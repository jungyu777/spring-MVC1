package solid.jsp.v1.mapperv1;



import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import solid.jsp.v1.entityv1.Board;

import java.util.List;

@Mapper
public interface BoardMapper {

    public List<Board> getLists();
    public void boardInsert(Board vo);
    public Board boardContent(int idx);
    public void boardDelete(int idx);
    public void boardUpdate(Board vo);
    @Update("update myboard set count=count+1 where idx=#{idx}")
    public void boardCount(int idx);
}
