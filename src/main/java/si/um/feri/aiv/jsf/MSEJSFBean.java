package si.um.feri.aiv.jsf;
import jakarta.ejb.Stateless;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import si.um.feri.aiv.dao.MSEMemoryDao;
import si.um.feri.aiv.dao.MSEDAO;
import si.um.feri.aiv.vao.MSE;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;
import java.util.ArrayList;
import jakarta.inject.Inject;
import si.um.feri.aiv.vao.Community;
import si.um.feri.aiv.dao.CommunityMemoryDao;
import si.um.feri.aiv.jsf.CommunityJSFBean;
@Named("mse")
@SessionScoped
public class MSEJSFBean implements Serializable {

    @Serial
    private static final long serialVersionUID = 1199069773058189490L;
    Logger log=Logger.getLogger(MSEJSFBean.class.toString());
    private MSEDAO dao=MSEMemoryDao.getInstance();
    private MSE selectedMSE=new MSE();
    private String selectedEmail;
    @Inject
    private CommunityJSFBean communityBean;
    public List<MSE> getAllMSEs() throws Exception {
        return dao.getAll();
    }


//    public String saveMSE() throws Exception {
//
//        MSE newMSE = new MSE();
//        newMSE.setEmail(selectedMSE.getEmail());
//        newMSE.setName(selectedMSE.getName());
//        newMSE.setSurname(selectedMSE.getSurname());
//        newMSE.setXcoordinates(selectedMSE.getXcoordinates());
//        newMSE.setYcoordinates(selectedMSE.getYcoordinates());
//        newMSE.setCapacity(selectedMSE.getCapacity());
//        dao.save(newMSE);
//        return "editcommunities";
//    }

    // Inside MSEJSFBean class
    public String saveMSE() {
        try {
            MSE newMSE = new MSE();
            newMSE.setEmail(selectedMSE.getEmail());
            newMSE.setName(selectedMSE.getName());
            newMSE.setSurname(selectedMSE.getSurname());
            newMSE.setXcoordinates(selectedMSE.getXcoordinates());
            newMSE.setYcoordinates(selectedMSE.getYcoordinates());
            newMSE.setCapacity(selectedMSE.getCapacity());
            Community currentCommunity = communityBean.getSelectedCommunity();
            currentCommunity.getIncludedMSEs().add(newMSE);

            communityBean.updateCommunity();

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "MSE saved successfully."));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "An error occurred while saving MSE."));
            e.printStackTrace();
        }

        return "editcommunities.xhtml?faces-redirect=true&includeViewParams=true";
    }
    public void deleteMSE(MSE o) throws Exception {
        dao.delete(o.getEmail());
    }

    public void setSelectedEmail(String email) throws Exception {
        this.selectedEmail = email;
        // Fetch the community to search its MSEs
        Community currentCommunity = communityBean.getSelectedCommunity();
        if(currentCommunity != null) {
            for(MSE mse : currentCommunity.getIncludedMSEs()) {
                if(mse.getEmail().equals(email)) {
                    this.selectedMSE = mse;
                    return; // Exit once the matching MSE is found
                }
            }
        }
        this.selectedMSE = new MSE(); // Reset if not found
    }
    public String editMSE() {
        selectedMSE = dao.find(selectedEmail);
        if (selectedMSE == null) {
            selectedMSE = new MSE();
        }
        return "editcommunities";
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
