package com.utez.geco.repository;

import com.utez.geco.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAll();
    User findByIdUser(long id);
    User save(User user);
    @Query(value = "SELECT * FROM user WHERE id_hotel = :idHotel", nativeQuery = true)
    List<User> findByHotel(@Param("idHotel") long idHotel);
    @Modifying
    @Query(value = "UPDATE user SET username = :username, email = :email, id_rol = :idRol, turn = :turn WHERE id_user = :id", nativeQuery = true)
    int updateEmailUsernameRol(@Param("username") String username, @Param("email") String email, @Param("idRol") long idRol, @Param("turn") int turn, @Param("id") long id);
    @Modifying
    @Query(value = "UPDATE user SET password = :password WHERE id_user = :id", nativeQuery = true)
    int changePassword(@Param("password") String password, @Param("id") long id);
    @Modifying
    @Query(value = "UPDATE user SET status = :change WHERE id_user = :id", nativeQuery = true)
    int changeStatus(@Param("change") int change, @Param("id") long id);
    @Query(value = "SELECT id_user FROM user ORDER BY id_user DESC LIMIT 1", nativeQuery = true)
    long findLastId();
    @Query(value = "SELECT * FROM user WHERE email = :email LIMIT 1", nativeQuery = true)
    User findByEmail(@Param("email") String email);
    @Query(value = "SELECT * FROM user WHERE username = :username LIMIT 1", nativeQuery = true)
    User findByUsername(@Param("username") String username);
    User findByEmailAndPassword(String email, String password);
    User findByUsernameAndPassword(String username, String password);
}
