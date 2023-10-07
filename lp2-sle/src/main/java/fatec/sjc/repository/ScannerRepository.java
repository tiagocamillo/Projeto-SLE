package fatec.sjc.repository;

import fatec.sjc.entity.Scanner;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ScannerRepository implements PanacheRepositoryBase<Scanner, Long> {
}
