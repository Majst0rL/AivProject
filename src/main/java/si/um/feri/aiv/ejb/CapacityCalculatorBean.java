package si.um.feri.aiv.ejb;
import jakarta.ejb.Stateless;
import jakarta.ejb.Local;
import jakarta.ejb.Remote;
import si.um.feri.aiv.service.CapacityCalculatorLocal;
import si.um.feri.aiv.service.CapacityCalculatorRemote;
import si.um.feri.aiv.dao.CommunityMemoryDao;
import si.um.feri.aiv.vao.Community;
import si.um.feri.aiv.vao.MSE;

@Stateless
public class CapacityCalculatorBean implements CapacityCalculatorRemote,CapacityCalculatorLocal{

    private CommunityMemoryDao communityDao = CommunityMemoryDao.getInstance();

    @Override
    public long getTotalCapacityForCommunity(String communityName) {
        long totalCapacity = 0;
        Community community = communityDao.find(communityName);
        if (community != null) {
            for (MSE mse : community.getIncludedMSEs()) {
                totalCapacity += mse.getCapacity();
            }
        }
        return totalCapacity;
    }
}
