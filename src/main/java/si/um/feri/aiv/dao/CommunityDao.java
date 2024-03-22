package si.um.feri.aiv.dao;

import jakarta.ejb.Local;
import si.um.feri.aiv.vao.Community;
import si.um.feri.aiv.vao.MSE;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Local
public interface CommunityDao {
    void save(Community community);
    Community find(String communityName);
    void delete(String communityName);
    void update(Community updatedCommunity);
    List<Community> getAll();

    MSE findMSEByEmail(String mseEmail);
    List<MSE> getAllMSEs();
}
