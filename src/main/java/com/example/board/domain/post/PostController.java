package com.example.board.domain.post;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
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

  @PostMapping("/post/save.do")
  public String savePost(final PostRequest params) {
    service.savePost(params);
    return "redirect:/post/list.do";
  }

  // 전체 리스트
  @GetMapping("/post/list.do")
  public String openPostList(Model model) {
    List<PostResponse> posts = service.findAllPost();
    model.addAttribute("posts", posts);
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
  public String updatePost(final PostRequest params) {
    service.updatePost(params);
    return "redirect:/post/list.do";
  }

  // 물리적 삭제가 아닌 논리 삭제이다.
  @PostMapping("/post/delete.do")
  public String deletePost(@RequestParam final Long id) {
    service.deletePost(id);
    return "redirect:/post/list.do";
  }
}
