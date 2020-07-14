# Proof of Skill - Mini-Regelsprachen-Interpreter

## Aufgabe
Schreiben Sie ein Java-Programm, das einen Ausdruck in der im folgenden beschriebenen Regelsprache einließt und seinen Wert berechnet:

- Die Sprache kennt die Datentypen Text und Dezimalzahl
- Textkonstanten werden in doppelten oder einfachen Anführungszeichen geschrieben
- Dezimalzahlen werden in deutscher Notation mit Tausenderpunkt geschrieben
- Die Sprache kennt Variablen. Variablennamen können Groß- und Kleinbuchstaben, Zahlen und Punkte enthalten
- In einem Variablennamen dürfen keine zwei Punkte nebeneinander stehen
- Variablennamen müssen mit einem Buchstaben beginnen
- Zahlen können mit den üblichen Operatoren (+, -, *, /) verknüpft werden. 
  Es gilt dabei die übliche Punkt-Vor-Strich-Regel
- Texte können mit dem Operator + verkettet werden, 
    andere Opertionen auf Texten werden nicht unterstützt
- Wenn eine Zahl mit einem Text verknüpft wird, dann wird die Zahl zunächst automatich
  in einen Text _im englischen Format_ umgeandelt.  

## Ablauf des Programms

- Das Programm liest zunächst eine Variablenbelegung aus einer Textdatei ein
- Die Textdatei enthält je Zeile eine Zuweisung in einer der folgenden Formen:
  - Text-Zuweisung: `Text.Variable.name="Ein Text"`  (hier brauchen nur doppelte Anführungszeichen unterstützt zu werden)
  - Zahlen-Zuweisung: `Zahl.Variable.name=123456.78` (hier genügt es wenn Zahlen im englischen Format richtig verarbeitet werden)
- Das Programm nimmt dann den Ausdruck in der oben beschriebenen Regelsprache entgegen,
  berechnet dessen Wert und gibt ihn aus. 
- Wie das Programm den Ausdruck entgegen nimmt (Kommandozeilenparameter, Datei auslesen,
  Standard-Eingabe, Eingabe über Webseite, ...) spielt keine Rolle - es geht uns hier
  um den Algorithmus zum interpretieren der Ausdrücke.
  
## Beispiele zur Illustration

Angenommen die Variablen-Belegungsdatei enthält folgende Einträge:

    ein.halb=0.5
    ein.praefix="Agent"

Dann sollten folgende Ausdrücke das jeweils angegebene Ergebnis liefern:

- `ein.praefix + ' ' + "00" + 14 * ein.halb` liefert den Text `Agent 007`
- `100.100,7 / 7` liefert die Zahl `14.300,1`
- `2 * (3 + (5 - 2))` liefert die Zahl `12`
- `ein.praefix - 3` liefert einen Fehler
- `kein.praefix` liefert einen Fehler
- `1000` liefert einen Fehler (der Tausenderpunkt fehlt)

## Rahmenbedingungen

- Tools wie antlr oder ähnliches dürfen gerne eingesetzt werden, sind aber nicht Pflicht.
- Das Programm soll sich über Maven bauen lassen und außer Java 1.8 (JDK) und Maven 3.6.x keine zuvor 
  installierte Software voraussetzen
- Außer der Erkennung von Ausdrücken deren Wert nicht berechnet werden kann erwarten wir 
  keine weitere Fehlerbehandlung. Insbesondere kann auf eine besondere Behandlung von 
  fehlerhaften Variablen-Belegungsdateien verzichtet werden  

## Zusatz-Herausforderung für Bewerber mit sehr hoher Qualifikation

In den obigen Anforderungen fehlen ein paar Angaben die eigentlich teil einer wirklich
vollständigen Spezifikation sein sollten. 
- Was haben wir "vergessen"?
- Wie haben Sie die Lücken gefüllt?  