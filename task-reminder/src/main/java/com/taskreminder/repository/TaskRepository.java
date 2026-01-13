package com.taskreminder.repository;

import com.taskreminder.model.Task;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

/*

 */

/**
 * Doc file
 */
@Repository
public class TaskRepository {

    private final JdbcTemplate jdbcTemplate;

    public TaskRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * @return
     */
    private final RowMapper<Task> rowMapper = (rs, rowNum) -> {
        Task task = new Task();

        task.setId(rs.getLong("id"));
        task.setTitle(rs.getString("title"));
        task.setDescription(rs.getString("description"));
        task.setCompleted(rs.getBoolean("completed"));

        Timestamp ts = rs.getTimestamp("due_date");
        task.setDueDate(ts != null ? ts.toLocalDateTime() : null);

        return task;
    };

    public void save(Task task) {
        String sql = """
            INSERT INTO tasks (id, title, description, due_date, completed)
            VALUES (?, ?, ?, ?, ?)
            """;

        jdbcTemplate.update(
                sql,
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                task.isCompleted()
        );
    }

    public List<Task> findAll() {
        String sql = "SELECT * FROM tasks";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public Optional<Task> findById(long id) {
        String sql = "SELECT * FROM tasks WHERE id = ?";
        List<Task> tasks = jdbcTemplate.query(sql, rowMapper, id);
        return tasks.stream().findFirst();
    }


    public void update(Task task) {
        String sql = """
            UPDATE tasks
            SET title = ?, description = ?, due_date = ?, completed = ?
            WHERE id = ?
            """;

        jdbcTemplate.update(
                sql,
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                task.isCompleted(),
                task.getId()
        );
    }
}
