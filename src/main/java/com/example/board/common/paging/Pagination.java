package com.example.board.common.paging;

import com.example.board.common.dto.SearchDto;
import lombok.Getter;

@Getter
public class Pagination {

  private int totalRecordCount; // 전체 데이터 수
  private int totalPageCount; // 전체 페이지 수
  private int startPage; // 첫 페이지 번호
  private int endPage; // 끝 페이지 번호
  private int limitStart; // LIMIT 시작 위치
  private boolean existPrevPage; // 이전 페이지 존재 여부
  private boolean existNextPage; // 다음 페이지 존재 여부

  public Pagination(int totalRecordCount, SearchDto params) {
    if (totalRecordCount > 0) {
      this.totalRecordCount = totalRecordCount;
      calculator(params);
      params.setPagination(this); // 마지막 로직을 통해 params에 계산된 페이지 정보(this)를 저장합니다.
      // 기존에는 서비스에서 처리되던 로직이 Pagination 객체가 생성되는 시점에 실행되도록 변경한 건데요.
      // Pagination으로 책임을 전가함으로써 페이징 적용에 필요한 비즈니스 로직 한 줄이 줄어들었습니다. (코드 리팩터링)
    }
  }

  private void calculator(SearchDto params) {
    //  전체 페이지 수 계산
    /* 근데, 10000개 넣어놨는데 왜 1000개..지?
     * 페이지 하단에 출력할 전체 페이지 개수를 의미합니다. 테이블에 1,000개의 데이터(레코드)가 있고, recordSize(페이지당 출력할 데이터 개수)가 10개라고 가정했을 때,
     * 전체 페이지 개수는 (1,000 / 10)의 결과인 100개가 됩니다.
     * */
    totalPageCount = ((totalRecordCount - 1) / params.getRecordSize()) + 1;

    // 현재 페이지 번호가 전체 페이지 수보다 큰 경우, 현재 페이지 번호에 전체 페이지 수 저장
    if (params.getPage() > totalPageCount) {
      params.setPage(totalPageCount);
    }

    // 첫 페이지 번호 계산
    startPage = ((params.getPage() - 1) / params.getPageSize()) * params.getPageSize() + 1;

    // 끝 페이지 번호 계산 => 1부터 시작이라 -1인가?..
    endPage = startPage + params.getPageSize() - 1;

    //  끝 페이지가 전페 페이지 수보다 큰 경우, 끝 페이지에 전체 페이지 수 저장
    if (endPage > totalPageCount) {
      endPage = totalPageCount;
    }

    // LIMIT 시작 위치 계산
    /*
     *	현재 findAll 쿼리의 LIMIT 구문에 사용되는 offset과 동일한 기능을 하는 변수입니다.
     * SearchDto의 offset을 대신해서 LIMIT 구문의 첫 번째 인자로 사용됩니다.
     * */
    limitStart = (params.getPage() - 1) * params.getRecordSize();

    // 이전 페이지 존재 여부 확인
    /*
     * 현재 위치한 페이지에서 startPage(첫 페이지 번호)가 1이 아니라는 건,
     * 이전 페이지가 존재한다는 걸 의미합니다.
     * */
    existPrevPage = startPage != 1;
    System.out.println("existPrevPage => " + existPrevPage);

    // 다음 페이지 존재 여부 확인
    /*
     * recordSize(페이지당 출력할 데이터 개수)가 10개,
     * endPage(끝 페이지 번호)가 10이라고 가정했을 때 (10 * 10) = 100이라는 결과가 나오는데요.
     * 만약 totalRecordCount(전체 데이터 개수)가 105개라면, 다음 페이지가 존재한다는 걸 의미합니다
     * */
    existNextPage = (endPage * params.getRecordSize()) < totalRecordCount;
    System.out.println("existNextPage => " + existNextPage);
  }
}
