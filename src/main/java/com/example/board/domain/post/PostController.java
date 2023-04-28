package com.example.board.domain.post;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
}
