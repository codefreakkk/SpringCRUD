package dao;

import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDao {

    @Autowired
    public JdbcTemplate jdbcTemplate;


    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int insertData(User user) {
        String sql = "INSERT INTO `user` (`name`, `address`) VALUES (?,?)";
        int affected = jdbcTemplate.update(sql, user.name, user.address);
        return affected;
    }

    public List<User> getAllUsers() {
        String sql = "SELECT * FROM user";
        return this.jdbcTemplate.query(sql, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setAddress(rs.getString("address"));
                return user;
            }
        });
    }

    public int deleteUser(String uid) {
        String sql = "DELETE FROM `user` WHERE id = ?";
        int row = jdbcTemplate.update(sql, Integer.parseInt(uid));
        return row;
    }

    public User getStudentDataById(String uid) {
        String sql = "SELECT * FROM `user` WHERE id = ?";
        User user = (User) jdbcTemplate.queryForObject(sql, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setAddress(rs.getString("address"));
                return user;
            }
        }, Integer.parseInt(uid));

        return user;
    }
}
