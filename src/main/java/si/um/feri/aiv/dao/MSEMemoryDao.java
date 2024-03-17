package si.um.feri.aiv.dao;

import si.um.feri.aiv.vao.Community;
import si.um.feri.aiv.vao.MSE;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;


public class MSEMemoryDao implements MSEDAO{
    private List<MSE> mseList = Collections.synchronizedList(new ArrayList<MSE>());
    private Logger log = Logger.getLogger(MSEMemoryDao.class.getName());

    private static MSEMemoryDao instance=null;
    public static synchronized MSEMemoryDao getInstance() {

        if(instance==null) instance=new MSEMemoryDao();
        return instance;

    }

    @Override
    public void save(MSE mse) {
        log.info("Saving mse" + mse);
        if(find(mse.getEmail()) != null){
            log.info("DAO editing" + mse);
            delete(mse.getEmail());
        }
        mseList.add(mse);
    }


    @Override
    public MSE find(String email) {
        for (MSE mse : mseList) {
            if (mse.getEmail().equals(email)) {
                log.info("Found MSE with email: " + email);
                return mse;
            }
        }
        log.info("MSE not found with email: " + email);
        return null; // Not found
    }

    @Override
    public void delete(String email) {
        MSE mseToRemove = find(email);
        if (mseToRemove != null) {
            mseList.remove(mseToRemove);
            log.info("Deleted MSE with email: " + email);
        } else {
            log.info("MSE not found for deletion with email: " + email);
        }
    }


    @Override
    public void update(MSE updatedMSE) {
        MSE existingMSE = find(updatedMSE.getEmail());
        if (existingMSE != null) {
            existingMSE.setXcoordinates(updatedMSE.getXcoordinates());
            existingMSE.setYcoordinates(updatedMSE.getYcoordinates());
            existingMSE.setName(updatedMSE.getName());
            existingMSE.setSurname(updatedMSE.getSurname());
            existingMSE.setCapacity(updatedMSE.getCapacity());
            log.info("Updated MSE with email: " + updatedMSE.getEmail());
        } else {
            log.info("MSE not found for update with email: " + updatedMSE.getEmail());
        }
    }


    @Override
    public List<MSE> getAll() {
        log.info("Retrieved all MSEs");
        return mseList;
    }


}