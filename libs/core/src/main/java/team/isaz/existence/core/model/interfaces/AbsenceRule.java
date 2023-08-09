package team.isaz.existence.core.model.interfaces;

public interface AbsenceRule {

    boolean applicable(Object o);

    boolean absent(Object o, ExistenceChecker checker);
}
