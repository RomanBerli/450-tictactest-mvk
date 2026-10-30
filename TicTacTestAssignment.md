# TicTacTest Assignment ❌⭕

Aktuell hat dieses [TicTacToe](https://en.wikipedia.org/wiki/Tic-tac-toe) Projekt keine Tests.

Das Ziel ist eine gute Testabdeckung mit [JUnit](https://junit.org/) und [AssertJ](https://assertj.github.io/doc/) zu
erreichen. Wir wollen hierbei nicht plain JUnit-Tests schreiben, sondern verschiedene Testing-Libraries nutzen. Wir
nehmen [JaCoCo](https://www.jacoco.org/jacoco/) als eine Metrik für "gut".

Umsetzung als bewertete Einzelarbeit über mehrere Lektionen. Benötigte Konzepte werden während des Projekts zusammen
erarbeitet.

> [!IMPORTANT]
> LLM Codegenerierung ist generell nicht erlaubt und der Originalcode soll nicht wesentlich modifiziert werden.
> Es sollen keine Mockingframeworks (wie Mockito) verwendet werden.

### Anforderungen / Bewertung

1. Unit Testing mit AssertJ. Es werden fluent-Assertions verwendet (nicht bloss langweilige `isTrue()`). Es werden
   Hilfsstrukturen für gut lesbare Tests verwendet.
2. Verwendung von ParameterizedTest wo möglich und Vermeidung von Coderepetition.
3. JaCoCo Coverage Reports werden erstellt sowie eine JaCoCo Test Coverage Verification welche eine Branch-Coverage von
   90 % enforced. Dies ist eine erwartete Metrik für die Tests.
4. StdIn/StdOut Testing mit Pioneer.
5. CI Pipeline mit allen Reports als Build Artifacts bei jedem Commit. Der Build ist grün.
6. Ein zusätzlicher, perfekt spielender Player.
7. Ein zusätzliches, selbst gewähltes Testingframework, automatisiert ausgeführt mit einem beispielhaften Test.
8. Automatisiertes Mutation Testing mit PITest.
9. Generell keine SonarLint Warnings und einheitlicher Codestyle. Bevorzuge das `var`-Keyword wo möglich. Es werden
   jeweils die aktuellsten Libraryversionen verwendet.
10. Abgabe pünktlich als privates GitHub Projekt.

---