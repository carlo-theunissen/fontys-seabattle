package communication;

/**
 * PackageCommunicatie geeft de mogelijkheid om pakketten te versturen
 * Hij handelt geen reacties af en stuurt het niet door naar een IGameLogic
 */
public interface PackageCommunication {

    /**
     * Verstuur een pakket via de communicatielaag.
     * @param communicationPackage
     */
    void sendPackage(CommunicationPackage communicationPackage);
}
