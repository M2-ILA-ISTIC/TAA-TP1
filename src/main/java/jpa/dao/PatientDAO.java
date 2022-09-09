package jpa.dao;

import jpa.po.Patient;

import javax.persistence.EntityManager;

public class PatientDAO {
    private EntityManager manager;

    public PatientDAO(EntityManager manager) {
        this.manager = manager;
    }

    public void createPatients(String firstName, String lastName) {
        manager.persist(new Patient(firstName, lastName));
    }

    public Object getPatientByName(String firstName, String lastName) {
        return manager
                .createQuery("SELECT p FROM Patient p WHERE p.lastName LIKE :lastName AND p.firstName LIKE :firstName")
                .setParameter("firstName", firstName)
                .setParameter("lastName", lastName)
                .getResultList()
                .get(0);
    }

    public Object getPatientId(String firstName, String lastName) {
        return manager
                .createQuery("SELECT p.id FROM Patient p WHERE p.lastName LIKE :lastName AND p.firstName LIKE :firstName")
                .setParameter("firstName", firstName)
                .setParameter("lastName", lastName)
                .getResultList()
                .get(0);
    }

    public void listPatients() {
        var listResults = manager
                .createQuery("Select p From Patient p", Patient.class)
                .getResultList();
        System.out.print("Number of patients: " + listResults.size());
        for (var next : listResults) {
            System.out.println("Next patient: " + next);
        }
    }
}
