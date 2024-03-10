package si.um.feri.aiv.jsf;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import si.um.feri.aiv.dao.MSEMemoryDao;
import si.um.feri.aiv.dao.MSEDAO;
import si.um.feri.aiv.vao.MSE;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

@Named("mse")
@SessionScoped
public class MSEJSFBean implements Serializable {

    @Serial
    private static final long serialVersionUID = 1199069773058189490L;
    Logger log=Logger.getLogger(MSEJSFBean.class.toString());

    private MSEDAO dao=new MSEMemoryDao();

    private MSE selectedMSE=new MSE();

    private String selectedEmail;

    public List<MSE> getAllMSEs() throws Exception {
        return dao.getAll();
    }

//    public String saveMSE() throws Exception {
//        dao.save(selectedMSE);
//        return "allmse";
//    }

    public String saveMSE() throws Exception {

        MSE newMSE = new MSE();
        newMSE.setEmail(selectedMSE.getEmail());
        newMSE.setName(selectedMSE.getName());
        newMSE.setSurname(selectedMSE.getSurname());
        newMSE.setXcoordinates(selectedMSE.getXcoordinates());
        newMSE.setYcoordinates(selectedMSE.getYcoordinates());
        newMSE.setCapacity(selectedMSE.getCapacity());
        dao.save(newMSE);
        return "allmse";
    }
    public void deleteMSE(MSE o) throws Exception {
        dao.delete(o.getEmail());
    }

    public void setSelectedEmail(String email) throws Exception {
        selectedEmail = email;
        selectedMSE = dao.find(email);
        if(selectedMSE == null) selectedMSE = new MSE();
    }
    public String editMSE() {
        selectedMSE = dao.find(selectedEmail);
        if (selectedMSE == null) {
            selectedMSE = new MSE();
        }
        return "allmse";
    }
    public String getSelectedEmail() {
        return selectedEmail;
    }

    public MSE getSelectedMSE() {
        return selectedMSE;
    }

    public void setSelectedMSE(MSE selectedMSE) {
        this.selectedMSE = selectedMSE;
    }
}
