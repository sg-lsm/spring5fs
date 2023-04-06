package spring;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Collection;
import java.util.List;

public class MemberDao{
    private JdbcTemplate jdbcTemplate;
    public MemberDao(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Member searchByEmail(String email) {
        //RowMapper는 ResultSet에서 데이터를 읽어와 Member 객체로 변환해주는 기능을 제공하므로 RowMapper의 타입파라미터로 Member를 사용
         /*
         // 1. 임의 클래스를 이용해 RowMaper의 객체를 전달하여 처리
        List<Member> results = jdbcTemplate.query("select * from MEMBER where EMAIL = ?", new RowMapper<Member>() {
            @Override

            public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
                Member member = new Member(
                        rs.getString("EMAIL"),
                        rs.getString("PASSWORD"),
                        rs.getString("NAME"),
                        rs.getTimestamp("REGDATE").toLocalDateTime()
                );
                member.setId(rs.getLong("ID"));
                return member;
            }
        },email); // 물음표 갯수만큼 전달

        */
        // 2. 람다로 표현
        /*
        List<Member> results = jdbcTemplate.query(
                "select * from MEMBER where EMAIL = ?",
                (ResultSet rs, int rowNum) ->{
                    Member member = new Member(
                            rs.getString("EMAIL"),
                            rs.getString("PASSWORD"),
                            rs.getString("NAME"),
                            rs.getTimestamp("REGDATE").toLocalDateTime()
                    );
                    member.setId(rs.getLong("ID"));
                    return member;
                },email
        );
        return results.isEmpty() ? null : results.get(0);
    }
         */
        List<Member> results = jdbcTemplate.query(
                "select * from MEMBER where EMAIL = ?",
                new MemberRowMapper(), email
        );
        return results.isEmpty() ? null : results.get(0);
    }

    public List<Member> selectAll(){
        List<Member> results = jdbcTemplate.query(
                "select * from MEMBER",
                new MemberRowMapper()
        );
        return results;
    }

    public int count(){
        Integer count = jdbcTemplate.queryForObject(
                "select count(*) from MEMBER", Integer.class
        );
        return count;
    }
    public void insert(Member member){
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement pstmt = con.prepareStatement(
                        "insert into MEMBER(EMAIL, PASSWORD, NAME, REGDATE)" + "values(?,?,?,?)",new String[]{"ID"});
                        pstmt.setString(1, member.getEmail());
                        pstmt.setString(2, member.getPassword());
                        pstmt.setString(3, member.getName());
                        pstmt.setTimestamp(4, Timestamp.valueOf(member.getRegDateTime()));
                        return pstmt;
            }
        },keyHolder);
        Number keyValue = keyHolder.getKey();
        member.setId(keyValue.longValue());

    }
    public void update(Member member){
        jdbcTemplate.update("update MEMBER set NAME=?, PASSWORD=? where EMAIL=?", member.getName(), member.getPassword(), member.getEmail());
        /*
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement pstmt = con.prepareStatement(
                        "insert into MEMBER(EMAIL, PASSWORD, NAME, REGDATE) values (?,?,?,?)");
                pstmt.setString(1, member.getEmail());
                pstmt.setString(2, member.getPassword());
                pstmt.setString(3, member.getName());
                pstmt.setTimestamp(4, Timestamp.valueOf(member.getRegDateTime()));
                return pstmt;
            }
        });
         */
    }
}