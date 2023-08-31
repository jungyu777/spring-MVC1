package solid.jsp.v1.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import solid.jsp.v1.entityv1.Board;
import solid.jsp.v1.mapperv1.BoardMapper;

import java.util.List;

@Controller
public class BoardControllerV1 {
    @Autowired
    private BoardMapper mapper;

    @RequestMapping("/boardList.do")
    public String boardList(Model model){
        List<Board> list = mapper.getLists(); //DB에서 가져온 데이터를
        model.addAttribute("list",list);
        return "boardList";
    }

    //a태그의 요청을 받을떄 @GetMapping으로 받을수 있다
    @GetMapping("/boardForm.do")
    public String boardForm(){
        return "boardForm";  //WEB-INF/views/boardForm.jsp -> forward
    }
    //클라이언트에서 어떤 새로운 요청이 생기면  컨트롤러로가서 요청을 받는 메서드를 만들고

    @PostMapping("/boardInsert.do")
    public String boardInsert(Board vo){ //파라메터수집 title, content, writer
        mapper.boardInsert(vo); //등록
        return "redirect:/boardList.do"; //redirect
    }

    @GetMapping("/boardContent.do")
    public String boardContent(@RequestParam("idx") int idx,Model model){ //?idx 는 ${vo.idx} 이다
        Board vo = mapper.boardContent(idx);//Mapper 에있는 메서드를 콜해서
        //조회수 증가
        mapper.boardCount(idx);
        model.addAttribute("vo",vo); //객체 바인딩을 해준다
        return "boardContent"; //boardContent.jsp
    }
    @GetMapping("/boardDelete.do/{idx}")
    public String boardDelete(@PathVariable("idx")int idx){ // ?idx 삭제할 번호가 넘어오면 받는 방법은 @RequestParam 으로 받던지
        mapper.boardDelete(idx);
        return "redirect:/boardList.do";
    }

    @GetMapping("/boardUpdateForm.do/{idx}")
    public String boardUpdateForm(@PathVariable("idx")int idx,Model model){
        Board vo = mapper.boardContent(idx);
        model.addAttribute("vo",vo);
        return "boardUpdate";
    }

    @PostMapping("/boardUpdate.do")
    public String boardUpdate(Board vo){  //idx, title, content
        mapper.boardUpdate(vo);
        return "redirect:/boardList.do";
    }

}
