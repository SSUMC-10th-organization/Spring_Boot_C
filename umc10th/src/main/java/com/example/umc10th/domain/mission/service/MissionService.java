package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.domain.mission.enums.MissionStatus;
import com.example.umc10th.domain.mission.exception.MissionException;
import com.example.umc10th.domain.mission.exception.code.MissionErrorCode;
import com.example.umc10th.domain.mission.repository.MemberMissionRepository;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import com.example.umc10th.domain.store.entity.Store;
import com.example.umc10th.domain.store.exception.StoreException;
import com.example.umc10th.domain.store.exception.code.StoreErrorCode;
import com.example.umc10th.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final StoreRepository storeRepository;

    // 회원 미션 목록 조회 (내가 진행 중인 미션 조회하기)_ 기존 페이지 하드코딩방식
//    public MissionResDTO.MissionListDTO getMissions(Long memberId, MissionStatus status) {
//
//        Page<MemberMission> page = memberMissionRepository.findByMemberIdAndStatus(
//                memberId, status, PageRequest.of(0, 10)
//        );
//
//        return MissionConverter.toMissionListDTO(page);
//    }

    // 미션6주차_1 수정 - 페이지 정보를 파라미터로 받음
    public MissionResDTO.MissionListDTO getMissions(
            Long memberId,
            MissionStatus status,
            Integer pageSize,
            Integer pageNumber
    ) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);

        Page<MemberMission> page = memberMissionRepository.findByMemberIdAndStatus(
                memberId, status, pageRequest
        );

        return MissionConverter.toMissionListDTO(page);
    }

    // 홈화면 - 도전 가능한 미션 목록
    public MissionResDTO.HomeMissionListDTO getHomeMissions(Long locationId, Long memberId, int page) {

        Page<Mission> missionPage = missionRepository.findMissionsByLocationId(
                locationId, memberId,PageRequest.of(page, 10)
        );

        return MissionConverter.toHomeMissionListDTO(missionPage);
    }


    // 미션 상태 업데이트 (진행중 → 진행완료) _아직 컨버터 적용안했습니다.
    @Transactional
    public MissionResDTO.UpdateMissionStatusDTO updateMissionStatus(Long memberMissionId, MissionStatus status) {

        MemberMission memberMission = memberMissionRepository.findById(memberMissionId)
                .orElseThrow(() -> new RuntimeException("미션을 찾을 수 없습니다."));

        MemberMission updated = MemberMission.builder()
                .id(memberMission.getId())
                .member(memberMission.getMember())
                .mission(memberMission.getMission())
                .isCompleted(status)
                .build();

        memberMissionRepository.save(updated);

        return MissionResDTO.UpdateMissionStatusDTO.builder()
                .memberMissionId(updated.getId())
                .status(updated.getIsCompleted())
                .build();
    }

    // 미션 생성
    @Transactional
    public Void createMission(Long storeId, MissionReqDTO.CreateMission dto) {
        // 가게 찾기 (없으면 예외)
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.NOT_FOUND));

        // DTO → 엔티티 변환
        Mission mission = MissionConverter.toMission(store, dto);

        // DB 저장
        missionRepository.save(mission);
        return null;
    }


    // 가게 미션 목록 조회(5.11)
    // 리스트방식
//    public List<MissionResDTO.GetMission> getMissions(Long storeId) {
//        // Spring Data JPA는 메서드이름자동 분석해서 쿼리만듬
//        // SELECT * FROM mission WHERE store_id = ?
//        List<Mission> missionList = missionRepository.findAllByStore_Id(storeId);
//
//        // Stream API로 엔티티 리스트 → DTO 리스트 변환
//        return missionList.stream()
//                .map(MissionConverter::toGetMission)
//                .toList();
//    }

    // 가게 미션 목록 조회
    // List에서 page사용
    // 1) 리스트 기반일 때
//    public MissionResDTO.Pagination<MissionResDTO.GetMission> getMissions(
//            Long storeId, Integer pageSize, Integer pageNumber, String sort
//    ) {
//        Sort sortInfo = (sort != null) ? Sort.by(sort) : Sort.by("id").descending();
//        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortInfo);
//
//        Page<Mission> missionList =
//                missionRepository.findAllByStore_Id(storeId, pageRequest);
//
//        return MissionConverter.toPagination(
//                missionList.map(MissionConverter::toGetMission).toList(),
//                missionList.getNumber(),   // 현재 페이지 번호
//                missionList.getSize()      // 페이지 크기
//        );
//    }

    // 2) 커서기반 (7주차 미션2)
    public MissionResDTO.Pagination<MissionResDTO.GetMission> getMissions(
            Long storeId, Integer pageSize, String cursor, String query
    ) {
        // 1. PageRequest 생성 (pageNumber는 0으로 고정)
        PageRequest pageRequest = PageRequest.of(0, pageSize);

        long idCursor;
        Slice<Mission> missionList;
        String nextCursor;

        // 2. 커서가 있는지 확인
        if (!cursor.equals("-1")){

            // 커서 분리: "id:383" → ["id", "383"]
            String[] cursorSplit = cursor.split(":");

            switch(query.toLowerCase()){
                case "id":
                    // 커서 타입 변환
                    long prevCursor = Long.parseLong(cursorSplit[0]);
                    idCursor = Long.parseLong(cursorSplit[1]);

                    // where절에 커서값 넣어 조회
                    missionList = missionRepository
                            .findMissionsByStore_IdAndIdLessThanOrderByIdDesc(
                                    storeId, idCursor, pageRequest
                            );
                    break;
                default:
                    throw new MissionException(MissionErrorCode.QUERY_NOT_VALID);
            }
        } else {
            // 커서 없이 조회 (첫 페이지)
            missionList = missionRepository
                    .findMissionsByStore_IdOrderByIdDesc(storeId, pageRequest);
        }

        // 3. 다음 커서 계산 (마지막 요소의 ID 사용)
        nextCursor = missionList.getContent().getLast().getId()
                + ":"
                + missionList.getContent().getLast().getId();

        // 4. 응답 DTO 생성
        return MissionConverter.toPagination(
                missionList.map(MissionConverter::toGetMission).toList(),
                missionList.hasNext(),   // 다음 데이터 존재 여부
                nextCursor,
                missionList.getSize()
        );
    }

}