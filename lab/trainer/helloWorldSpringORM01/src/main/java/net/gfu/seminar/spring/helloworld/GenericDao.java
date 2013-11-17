package net.gfu.seminar.spring.helloworld;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * Generisches Datenzugriffsobjekt (Data Access Object/DAO). Ein DAO kapselt den
 * Zugriff auf einen persistenten Speicher.
 * <p>
 * Jede Instanz einer persistenten Klasse vom Typ <code>T</code> muss über einen
 * Object IDentifier (OID) vom Typ <code>ID</code> eindeutig identifizierbar
 * sein. Dabei gilt, dass die JVM Identität zweier Instanzen von T
 * <code>T == T</code> (numerically identical) ungleich der qualitativen
 * Identität (qualitative identity bzw. Gleichheit) sein kann
 * <code>T.equals(T)</code>. Es sollte für zwei fachlich identische Instanzen
 * von T aber gelten: <code>T.getId().equals(T.getId)</code>. T hat eine
 * Getter-Methode mit der Signatur: <code>ID T.getId()</code>.
 * <p>
 * Die <code>equals</code> -Methode von T verwendetet diese persistente
 * Identität (alias entity identity, persistent identity, OID).
 * 
 * @param <T>
 *            Typ der persistenten Klasse
 * @param <ID>
 *            Identifizierender Schlüssel (OID)
 * @see <a
 *      href="http://java.sun.com/blueprints/corej2eepatterns/Patterns/DataAccessObject.html">Sun
 *      J2EE Patterns - DAO</a>
 * @see <a
 *      href="http://www-128.ibm.com/developerworks/java/library/j-genericdao.html"
 *      >Don't repeat the DAO!</a>
 * 
 */
public interface GenericDao<T, ID extends Serializable> {

	ID create(T entity);

	List<ID> create(List<T> entityList);

	T findById(ID id);

	List<T> findAll();

	List<T> findByExample(T exampleInstance);

	T update(T entity);

	void delete(T entity);

	void delete(Class<T> clazz, ID id);

	void delete(Collection<T> entities);

}