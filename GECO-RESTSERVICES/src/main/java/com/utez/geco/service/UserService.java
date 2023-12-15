package com.utez.geco.service;

import com.utez.geco.DTO.UpdUsrDTO;
import com.utez.geco.DTO.UsrChgPassDTO;
import com.utez.geco.model.User;
import com.utez.geco.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository ur;

    public List<User> findAll() {
        return ur.findAll();
    }

    public List<User> findByHotel(long idHotel) {
        return ur.findByHotel(idHotel);
    }

    public User findById(long id) {
        return ur.findByIdUser(id);
    }

    public boolean save(User user) {
        User saved = ur.save(user);
        return saved != null;
    }

    public long findLastId() {
        return ur.findLastId();
    }

    public boolean updateEmailUsernameRol(UpdUsrDTO updUsrDTO) {
        int updated = ur.updateEmailUsernameRol(
                updUsrDTO.getUsername(),
                updUsrDTO.getEmail(),
                updUsrDTO.getIdRol().getIdRol(),
                updUsrDTO.getTurn(),
                updUsrDTO.getIdUser());
        return updated != 0;
    }

    public boolean changePassword(UsrChgPassDTO dto) {
        if(dto.getCurrent().equals(dto.getConfirmation())) {
            int updated = ur.changePassword(dto.getChange(), dto.getIdUser());
            return updated != 0;
        } else {
            return false;
        }
    }

    public boolean changeStatus(User user, long id) {
        int change = user.getStatus() == 1 ? 0 : 1;
        int updated = ur.changeStatus(change, id);

        return updated != 0;
    }

   public User login(String user, String password) {
        boolean flag = false;
        User found = ur.findByEmail(user);
        if(found == null) {
            found = ur.findByUsername(user);
        }

        if(found != null) {
            flag = new BCryptPasswordEncoder().matches(password, found.getPassword());
            return found;
        } else {
            return null;
        }
   }
}
