# Zara UI Automation

![GitHub](https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white)
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Selenium](https://img.shields.io/badge/selenium-%2343B02A.svg?style=for-the-badge&logo=selenium&logoColor=white)
![TestNG](https://img.shields.io/badge/testng-%23F2A400.svg?style=for-the-badge&logo=testng&logoColor=white)
![Maven](https://img.shields.io/badge/maven-%23C71A36.svg?style=for-the-badge&logo=apachemaven&logoColor=white)
![Allure](https://img.shields.io/badge/allure-%235D2B86.svg?style=for-the-badge&logo=allure&logoColor=white)



> POM-based UI automation for Zara web with Selenium + TestNG + Maven.  
> Fast, readable tests, grouped by smoke and regression, with Allure reporting.

---

## Highlights
- Clean Page Object Model with a minimal `BasePage`
- Readable locators and explicit waits.
- Allure reports: trends, steps, attachments

---

## Tech Stack
| Tool | Purpose |
|---|---|
| **Java 23** | Core language |
| **Selenium 4** | Browser automation |
| **TestNG** | Grouping & assertions |
| **Maven** | Build & dependencies |
| **Allure** | Reporting |

---

## Project Structure
```
src/
 ├─ main/java/
 │   ├─ core/BasePage.java
 │   └─ pages/
 │       ├─ HomePage.java
 │       ├─ ManAllCatalogPage.java
 │       ├─ ProductDetailPage.java
 │       └─ SignUpPage.java
 └─ test/java/tests/
     ├─ BaseTest.java
     ├─ negative/
         └─ SignUpNegativeTest.java
     ├─ positive/
         └─ SignUpPositiveTest.java
     ├─ smoke/
     │   ├─ HomeSmokeTest.java
     │   ├─ ManAllProductsSmokeTest.java
     │   └─ ProductDetailSmokeTest.java
     └─ regression/
         ├─ HomeRegressionTest.java
         ├─ ManAllProductsRegressionTest.java
         └─ HomeRegressionTest.java
```

---

## Test Coverage
| Group | What it checks |
|---|---|
| **Smoke** | Home navigation, product listing visibility, cart add/remove basics |
| **Regression** | Logo routing, page consistency, edge cart behaviors |
| **Negative** | Required fields validation, invalid email formats, weak/short password rejection, disallowed characters/length boundaries. |
| **Positive** | Successful account creation with valid data, password meeting policy, optional fields handled correctly. |

---

## How to Run
**Prereqs:** JDK 23, Maven 3.9+, Google Chrome

```bash
# All tests
mvn clean test

# By TestNG group
mvn -Dgroups=smoke test
mvn -Dgroups=regression test
mvn -Dgroups=negative test
mvn -Dgroups=positive test
```

### Allure Report
```bash
# Generate and open
mvn allure:serve
```

