# REST Currency Converter

## Opis 
Usługa REST umożliwiająca przeliczanie walut stworzona w ramach zadania rekrutacyjnego. Aktualne kursy walut pobierane są z API Narodowego Banku Polskiego podczas uruchamiania aplikacji, a następnie raz na dzień odświeżane. Usługa może być wywoływana zarówno metodą GET jak i POST. W przypadku błędnego kodu waluty zwracana jest informacja o błędzie. Aplikacja została zaprojektowana tak, aby można było w przyszłości w łatwym sposób dodać pobieranie kursów walut z innego API.

## Technologie
Technologie i biblioteki wykorzystane przy budowie aplikacji:
- Spring Boot
- REST
- Maven
- jUnit

## Budowanie i uruchamianie aplikacji
### Uruchamianie aplikacji na wbudowanym serwerze Tomcat
Uruchomienie aplikacji na wbudowanym serwerze Tomcat umożliwia polecenie:

`mvn spring-boot:run`

W celu uproszczenia uruchamiania aplikacji został utworzony skrypt **start.bat**, który wykonuje powyższe polecenie i uruchamia aplikację.


### Budowanie aplikacji do pliku WAR
Zbudowanie aplikacji do pliku WAR umożliwia polecenie:

`mvn package`

W celu uproszczenia budowania aplikacji został utworzony skrypt **build.bat**, który wykonuje powyższe polecenie i buduje aplikację.


### Uruchomienie aplikacji na zewnętrznym serwerze aplikacji
W celu uruchomienia aplikacji na zewnętrznym serwerze należy wgrać na niego plik WAR znajdujący się w folderze **target**.


## Dostępne serwisy REST
### GET
Ścieżka: `/convert`

Parametry:
- amount – kwota do przeliczenia
- baseCurrency – kod waluty podanej kwoty
- targetCurrency – kod waluty docelowej

Przykładowe zapytanie:

`http://localhost:8080/convert?amount=100&baseCurrency=USD&targetCurrency=PLN`

### POST
Ścieżka: `/convert`

Przykładowe zapytanie:
```
http://localhost:8080/convert
POST
{
	"amount": 100,
	"baseCurrency": "USD",
	"targetCurrency": "PLN"
}
```
