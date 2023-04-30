package com.example.board.common.paging;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class PagingResponse<T> {
  // PostResponse 데이터가 들어올 것
  private List<T> list = new ArrayList<>();
  private Pagination pagination;

  public PagingResponse(List<T> list, Pagination pagination) {
    this.list.addAll(list);
    this.pagination = pagination;
  }
}

/*
 * list - 자바의 제네릭(Generic)을 활용해 보았습니다. T는 Type을 의미하며, 어떤 타입의 객체던 데이터로 받겠다는 걸 의미합니다.
 * pagination	계산된 페이지 정보를 해당 변수에 저장합니다.
 * */