package fatec.sjc.service;

import fatec.sjc.entity.Scanner;
import fatec.sjc.repository.ScannerRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class ScannerService {

    @Inject
    private ScannerRepository scannerRepository;

    @Transactional
    public Scanner criarScanner(Scanner scanner) {
        scannerRepository.persist(scanner);
        return scanner;
    }

    @Transactional
    public Scanner atualizarScanner(Long id, Scanner scanner) {
        Scanner scannerExistente = scannerRepository.findById(id);
        if (scannerExistente != null) {
            scannerRepository.persist(scannerExistente);
            return scannerExistente;
        } else {
            return null;
        }
    }

    @Transactional
    public void deletarScanner(Long id) {
        Scanner scanner = scannerRepository.findById(id);
        if (scanner != null) {
            scannerRepository.delete(scanner);
        }
    }

    public Scanner buscarScannerPorId(Long id) {
        return scannerRepository.findById(id);
    }

    public List<Scanner> listarScanners() {
        return scannerRepository.listAll();
    }
}
