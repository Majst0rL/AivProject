package si.um.feri.aiv.dao;
import si.um.feri.aiv.vao.Community;
import si.um.feri.aiv.vao.MSE;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class CommunityMemoryDao implements CommunityDao{
    private List<Community> communityList;
    private Logger log = Logger.getLogger(CommunityMemoryDao.class.getName());
    private List<MSE> mseList;

    public CommunityMemoryDao() {
        communityList = new ArrayList<>();
        mseList = new ArrayList<>();
    }

    @Override
    public void save(Community community) {
        communityList.add(community);
        log.info("Saved Community: " + community);
    }

    @Override
    public Community find(String communityName) {
        for (Community community : communityList) {
            if (community.getCommunityName().equals(communityName)) {
                log.info("Found Community with name: " + communityName);
                return community;
            }
        }
        log.info("Community not found with name: " + communityName);
        return null; // Not found
    }

    @Override
    public void delete(String communityName) {
        Community communityToRemove = find(communityName);
        if (communityToRemove != null) {
            communityList.remove(communityToRemove);
            log.info("Deleted Community with name: " + communityName);
        } else {
            log.info("Community not found for deletion with name: " + communityName);
        }
    }

    @Override
    public void update(Community updatedCommunity) {
        Community existingCommunity = find(updatedCommunity.getCommunityName());
        if (existingCommunity != null) {
            existingCommunity.setIncludedMSEs(updatedCommunity.getIncludedMSEs());
            existingCommunity.setBossName(updatedCommunity.getBossName());
            existingCommunity.setBossSurname(updatedCommunity.getBossSurname());
            existingCommunity.setBossEmail(updatedCommunity.getBossEmail());
            log.info("Updated Community with name: " + updatedCommunity.getCommunityName());
        } else {
            log.info("Community not found for update with name: " + updatedCommunity.getCommunityName());
        }
    }

    @Override
    public List<Community> getAll() {
        log.info("Retrieved all Communities");
        return communityList;
    }
    public MSE findMSEByEmail(String mseEmail) {
        for (Community community : communityList) {
            for (MSE mse : community.getIncludedMSEs()) {
                if (mse.getEmail().equals(mseEmail)) {
                    log.info("Found MSE with email: " + mseEmail);
                    return mse;
                }
            }
        }
        log.info("MSE not found with email: " + mseEmail);
        return null; // Not found
    }

    @Override
    public List<MSE> getAllMSEs() {
        log.info("Retrieved all MSEs");
        return mseList;
    }
}
