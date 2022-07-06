package ua.kpi.fict.acts.it03.proga.lab12;

@FunctionalInterface
public interface FInterface<T,R,P> {
  public abstract T get(R aParam, P bParam);
}