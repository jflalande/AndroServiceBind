package andro.jf;

/* C'est l'interface utilisée de part et d'autre (activité / service).
 * Le service va créer un objet de type Binder qui implémente cette interface.
 * L'activité va recevoir cet objet (sans connaitre son type) mais sait qu'il
 * implémentera cette interface.
 */
public interface AndroServiceInterface {

  public int getInfo();

}
