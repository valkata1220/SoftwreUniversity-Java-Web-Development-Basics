package exam.web.beans;

import exam.domain.models.binding.DocumentScheduleBindingModel;
import exam.domain.models.service.DocumentServiceModel;
import exam.service.DocumentService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@Named
@RequestScoped
public class DocumentScheduleBean {

    private DocumentScheduleBindingModel model;

    private DocumentService documentService;
    private ModelMapper modelMapper;

    public DocumentScheduleBean() {
    }

    @Inject
    public DocumentScheduleBean(DocumentService documentService, ModelMapper modelMapper) {
        this.documentService = documentService;
        this.modelMapper = modelMapper;
        this.initModel();
    }

    private void initModel() {
        this.model = new DocumentScheduleBindingModel();
    }

    public DocumentScheduleBindingModel getModel() {
        return model;
    }

    public void setModel(DocumentScheduleBindingModel model) {
        this.model = model;
    }

    public void schedule() throws IOException {
        DocumentServiceModel document = this.documentService.scheduleDocument(this.modelMapper.map(this.model, DocumentServiceModel.class));
        if(document == null){
            throw new IllegalArgumentException("Something went wrong!");
        }

        String url = "/details/" + document.getId();
        FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .redirect(url);
    }
}
