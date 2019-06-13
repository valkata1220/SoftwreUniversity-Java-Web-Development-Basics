package exam.web.beans;

import exam.domain.models.view.DocumentPrintViewModel;
import exam.service.DocumentService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Named
@RequestScoped
public class DocumentPrintBean {

    private DocumentService documentService;
    private ModelMapper modelMapper;

    public DocumentPrintBean() {
    }

    @Inject
    public DocumentPrintBean(DocumentService documentService, ModelMapper modelMapper) {
        this.documentService = documentService;
        this.modelMapper = modelMapper;
    }

    public DocumentPrintViewModel getDocument(String id) {
        DocumentPrintViewModel document = this.modelMapper.map(this.documentService.findDocumentById(id),DocumentPrintViewModel.class);
        return document;
    }

    public void delete() throws IOException {
        String id = ((HttpServletRequest) FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getRequest()).getParameter("id");

        this.documentService.delete(id);

        FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .redirect("/home");
    }
}
