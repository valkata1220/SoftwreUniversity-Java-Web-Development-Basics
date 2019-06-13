package exam.web.beans;

import exam.domain.models.service.DocumentServiceModel;
import exam.domain.models.view.DocumentDetailViewModel;
import exam.service.DocumentService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class DocumentDetailBean {

    private DocumentDetailViewModel model;

    private DocumentService documentService;
    private ModelMapper modelMapper;

    public DocumentDetailBean() {
    }

    @Inject
    public DocumentDetailBean(DocumentService documentService, ModelMapper modelMapper) {
        this.documentService = documentService;
        this.modelMapper = modelMapper;
        this.initModel();
    }

    private void initModel() {
        String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");

        DocumentServiceModel document = this.documentService.findDocumentById(id);

        if(document == null){
            throw new IllegalArgumentException("Something went wrong!");
        }

        this.model = this.modelMapper.map(document,DocumentDetailViewModel.class);
    }

    public DocumentDetailViewModel getModel() {
        return model;
    }

    public void setModel(DocumentDetailViewModel model) {
        this.model = model;
    }
}
