package si.um.feri.aiv.dao;

import jakarta.ejb.Local;
import si.um.feri.aiv.vao.Community;
import si.um.feri.aiv.vao.MSE;
import java.util.List;

@Local
public interface MSEDAO {

    void save(MSE mse);
    MSE find(String email);
    void delete(String email);
    List<MSE> getAll();
    void update (MSE mse);

}
