package si.um.feri.aiv.dao;

import si.um.feri.aiv.vao.Community;
import si.um.feri.aiv.vao.MSE;
import java.util.List;
public interface MSEDAO {

    void save(MSE mse);
    MSE find(String email);
    void delete(String email);
    List<MSE> getAll();
    void update (MSE mse);

}
