## 📊 Test Reports (Allure)

This project includes reports generated with Allure to visualize the execution of automated tests.

🔗 View report:
👉 https://Camilo-Cuapio.github.io/selenium-java-automation-Swaglabs/

---

## ⚙️ Report Deployment

## NOTE: Lessons Learned
During the configuration and deployment of Allure Reports, a common problem arose when switching between branches because generated files were being tracked by Git.

### 🔴 Problem

The `allure-report/` directory was mistakenly added to version control in the `main` branch. This caused conflicts when trying to switch to the `gh-pages` branch, as Git prevented overwriting tracked and untracked files.

### ✅ Solution

1. `allure-report` was removed from version control:

``bash
git rm -r --cached allure-report
```

2. The generated folders were added to the `.gitignore` file:

``
allure-report/
target/
```

3. A cleanup commit was performed on the repository.

4. The switch to the `gh-pages` branch was successfully completed.

5. The Allure report was generated from the execution results:

``bash
mvn clean test
allure generate target/allure-results --clean -o allure-report
```

6. The report files (not the entire folder) were copied to the `gh-pages` branch.

7. The report was published using the following commands:

``bash
git add .

`` git commit -m "deploy: publish allure report"

git push origin gh-pages --force

``

### 🎯 Result

The Allure report was successfully published using GitHub Pages and is now available via a public URL for portfolio and demonstration purposes.

### 🧠 Key Lessons

* Always ignore generated files (`target/`, `allure-report/`)
* Use a dedicated branch (`gh-pages`) for deployment
* Ensure `index.html` is in the root of the published branch
* Do not mix source code with generated files in the same branch

//Pages Configuration
PROJECT / SETTINGS / PAGES /BRANCH / gh-pages /(root) / save

//Update Allure Report

mvn clean test
allure generate target/allure-results --clean -o allure-report
git checkout gh-pages
git rm -rf .
xcopy /E /I /Y allure-report\* .
git add .

git commit -m "update report"
git push origin gh-pages --force
