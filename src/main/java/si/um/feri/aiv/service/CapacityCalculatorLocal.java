package si.um.feri.aiv.service;
import jakarta.ejb.Local;
@Local
public interface CapacityCalculatorLocal {
    long getTotalCapacityForCommunity(String communityName);
}
