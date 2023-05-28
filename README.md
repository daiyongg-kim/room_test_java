# room_test_java

@Dao
public interface TodoDao {

@Query("SELECT * FROM Todo")
List<Todo> getAll();
    @Insert
    void insert(Todo todo);

    @Update
    void update(Todo todo);

    @Delete
    void delete(Todo todo);

    @Query("DELETE FROM Todo")
    void deleteAll();
}
