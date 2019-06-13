package exam.service;

import exam.domain.models.service.DocumentServiceModel;

import java.util.List;

public interface DocumentService {

    DocumentServiceModel scheduleDocument(DocumentServiceModel documentServiceModel);

    DocumentServiceModel findDocumentById(String id);

    List<DocumentServiceModel> findAllDocuments();

    void delete(String id);
}
