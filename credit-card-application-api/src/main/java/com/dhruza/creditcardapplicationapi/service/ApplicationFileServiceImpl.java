package com.dhruza.creditcardapplicationapi.service;

import com.dhruza.creditcardapplicationapi.exception.ApplicationFileException;
import com.dhruza.creditcardapplicationapi.model.Applicant;
import com.dhruza.creditcardapplicationapi.model.ApplicationFile;
import com.dhruza.creditcardapplicationapi.model.StatusType;
import com.dhruza.creditcardapplicationapi.repository.ApplicantRepository;
import com.dhruza.creditcardapplicationapi.repository.ApplicationFileRepository;
import com.dhruza.creditcardapplicationapi.repository.ApplicationStatusRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Set;

@Service
@AllArgsConstructor
public class ApplicationFileServiceImpl implements ApplicationFileService {

    private final ApplicationFileRepository applicationFileRepository;
    private final ApplicationStatusRepository applicationStatusRepository;
    private final ApplicantRepository applicantRepository;

    @Transactional
    public ApplicationFile persistFileMetadata(ApplicationFile file) {
        return applicationFileRepository.save(file);
    }

    @Transactional(readOnly = true)
    public Set<ApplicationFile> findAllFilesByApplicantAndStatus(Long id, StatusType statusType) {
        return applicationFileRepository.findApplicationFilesByApplicantAndStatus(id, statusType);
    }

    @Transactional()
    public void removeApplicantFromApplicationFile(Applicant applicant) {
        applicant.getApplicationFiles().forEach( applicationFile -> {
            applicationFile.setApplicant(null);
            applicationFile.setFileStatus(applicationStatusRepository
                    .findByStatusType(StatusType.INACTIVE).get());
        });
        applicantRepository.save(applicant);
    }

    public String generateTxtFile(Applicant applicant) {
        try {
            final Path filePath = createFilePath(applicant.getOib(), ".txt");
            writeToTxtFile(filePath.toFile(), applicant);
            return filePath.toString();
        } catch (IOException e) {
            throw new ApplicationFileException(e.getMessage(), e);
        }
    }

    private Path createFilePath(String oib, String fileType) {
        try {
            Path documentDirPath = Paths.get("assets/files");
            documentDirPath = Files.createDirectories(documentDirPath);
            final Path documentFileName = Paths.get(
                    new Date().getTime() + "-" + oib + fileType);

            return documentDirPath.resolve(documentFileName);

        } catch (IOException e) {
            throw new ApplicationFileException(e.getMessage(), e);
        }
    }

    private void writeToTxtFile(File toFile, Applicant applicant) throws IOException {
        try (PrintWriter pw = new PrintWriter(toFile)) {
            StringBuilder sb = new StringBuilder();
            String TXT_DELIMITER = "|";
            String applicantString = sb.append(applicant.getFirstname())
                    .append(TXT_DELIMITER)
                    .append(applicant.getLastname())
                    .append(TXT_DELIMITER)
                    .append(applicant.getOib())
                    .append(TXT_DELIMITER)
                    .append(applicant
                            .getStatus()
                            .getStatusType()
                            .getName())
                    .toString();
            pw.write(applicantString);
        }
    }
}
