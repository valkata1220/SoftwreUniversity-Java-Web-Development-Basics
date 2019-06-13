package exam.web.beans;

import exam.domain.models.view.DocumentHomeViewModel;
import exam.service.DocumentService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class HomeBean {

    private List<DocumentHomeViewModel> models;

    private DocumentService documentService;
    private ModelMapper modelMapper;

    public HomeBean() {
    }

    @Inject
    public HomeBean(DocumentService documentService, ModelMapper modelMapper) {
        this.documentService = documentService;
        this.modelMapper = modelMapper;
        this.initModel();
    }

    private void initModel() {
        this.models = this.documentService.findAllDocuments()
                .stream()
                .map((d) ->{
                    DocumentHomeViewModel doc = this.modelMapper.map(d, DocumentHomeViewModel.class);
                    if(doc.getTitle().length() <= 12){
                        return doc;
                    }else {
                        doc.setTitle(doc.getTitle().substring(0,13) + "...");
                        return doc;
                    }})
                .collect(Collectors.toList());
    }

    public List<DocumentHomeViewModel> getModels() {
        return models;
    }

    public void setModels(List<DocumentHomeViewModel> models) {
        this.models = models;
    }
}
