package com.example.board.domain.post;

import com.example.board.common.dto.MessageDto;
import com.example.board.common.dto.SearchDto;
import com.example.board.common.paging.PagingResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//i  컨트롤러의 게시글 생성/수정/삭제 메서드에서 showMessageAndRedirect( )를 호출해 주기만 하면 됩니다.
@Controller
@Slf4j
public class PostController {

  private final PostService service;

  public PostController(PostService service) {
    this.service = service;
  }

  //  게시글 작성 페이지
  @GetMapping("/post/write.do")
  /*
   * 신규 게시글 등록에는 게시글 번호(id)가 필요하지 않기 때문에 required 속성을 false로 지정합니다.
   * 필수(required) 속성은 default 값이 true이며, required 속성을 false로 지정하지 않으면,
   * id가 파라미터로 넘어오지 않았을 때 예외가 발생합니다.
   * */
  public String openPostWrite(@RequestParam(value = "id", required = false) final Long id, Model model) {

    if (id != null) {
      PostResponse post = service.findPostById(id);
      model.addAttribute("post", post);
    }
    return "post/write";
  }

  // 신규 게시글 생성
  @PostMapping("/post/save.do")
  public String savePost(final PostRequest params, Model model) {
    service.savePost(params);
    MessageDto message = new MessageDto("게시글 생성 완료", "/post/list.do", RequestMethod.POST, null);
    return showMessageAndRedirect(message, model);
  }

  // 전체 리스트
  @GetMapping("/post/list.do")
  public String openPostList(Model model, @ModelAttribute("params") final SearchDto params) {
    PagingResponse<PostResponse> response = service.findAllPost(params);
    model.addAttribute("response", response);
    return "post/list";
  }

  //  게시글 상세정보
  @GetMapping("/post/view.do")
  public String openPostView(@RequestParam final Long id, Model model) {
    PostResponse post = service.findPostById(id);
    model.addAttribute("post", post);
    return "post/view";
  }

  //  수정
  @PostMapping("/post/update.do")
  public String updatePost(final PostRequest params, Model model, final SearchDto queryParams) {
    log.info("수완 queryParams => {}" + queryParams);
    service.updatePost(params);
    MessageDto message = new MessageDto("글이 수정됨", "/post/list.do", RequestMethod.GET, queryParamsToMap(queryParams));
    return showMessageAndRedirect(message, model);
  }

  // 물리적 삭제가 아닌 논리 삭제이다.

  /*
   * 기존에는 파라미터로 게시글 번호(id)만 수집했으나,
   * 이전 페이지 정보까지 수집하기 위해 SearchDto 타입의 객체인 queryParams를 파라미터로 선언해 주었습니다.
   * */
  @PostMapping("/post/delete.do")
  public String deletePost(@RequestParam final Long id, Model model, final SearchDto queryParams) {
    service.deletePost(id);
    MessageDto message = new MessageDto("글이 삭제됨", "/post/list.do", RequestMethod.GET, queryParamsToMap(queryParams));
    return showMessageAndRedirect(message, model);
  }

  // 쿼리 스트링 파라미터를 Map에 담아 반환
  /*
   * 해당 메서드는 deletePost( )에서 수집한 이전 페이지 정보(queryParams의 모든 멤버)를 Map에 담아 리턴해주는 역할을 하는데요.
   * MessageDto의 생성자는 마지막 파라미터로 Map을 전달받아 객체를 생성하기 때문에 컨트롤러에 해당 메서드를 추가해 주었습니다.
   * 참고로, 해당 기능은 컨트롤러에서 알러트 메시지 처리하기 기능의 연장선입니다. 추가적으로 뷰(HTML)로 전달할 데이터가 있다면, 유사한 방법으로 처리해 주시면 됩니다.
   * */
  private Map<String, Object> queryParamsToMap(final SearchDto queryParams) {
    Map<String, Object> map = new HashMap<>();
    map.put("page", queryParams.getPage());
    map.put("recordSize", queryParams.getRecordSize());
    map.put("pageSize", queryParams.getPageSize());
    map.put("keyword", queryParams.getKeyword());
    map.put("searchType", queryParams.getSearchType());
    return map;
  }

  //  사용자에게 메시지를 전달하고 페이지를 리다이렉트
  private String showMessageAndRedirect(final MessageDto params, Model model) {
    model.addAttribute("params", params);
    return "common/messageRedirect";
  }
}
