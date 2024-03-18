package si.um.feri.aiv.service;
import jakarta.ejb.Remote;

@Remote
public interface CapacityCalculatorRemote {
    long getTotalCapacityForCommunity(String communityName);
}
