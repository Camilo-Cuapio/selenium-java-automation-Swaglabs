## 📊 Reporte de Pruebas (Allure)

Este proyecto incluye reportes generados con Allure para visualizar la ejecución de pruebas automatizadas.

🔗 Ver reporte:
👉 https://Camilo-Cuapio.github.io/selenium-java-automation-Swaglabs/

---

## ⚙️ Despliegue del reporte

## NOTA: Experiencias aprendidas
Durante la configuración y publicación de Allure Reports, se presentó un problema común al cambiar entre ramas debido a que archivos generados estaban siendo rastreados por Git.

### 🔴 Problema

El directorio `allure-report/` fue agregado por error al control de versiones en la rama `main`. Esto provocó conflictos al intentar cambiar a la rama `gh-pages`, ya que Git evitaba sobrescribir archivos rastreados y no rastreados.

### ✅ Solución

1. Se eliminó `allure-report` del control de versiones:

   ```bash
   git rm -r --cached allure-report
   ```

2. Se agregaron las carpetas generadas al archivo `.gitignore`:

   ```
   allure-report/
   target/
   ```

3. Se realizó un commit para limpiar el repositorio.

4. Se pudo cambiar correctamente a la rama `gh-pages`.

5. Se generó el reporte de Allure a partir de los resultados de ejecución:

   ```bash
   mvn clean test
   allure generate target/allure-results --clean -o allure-report
   ```

6. Se copiaron los archivos del reporte (no la carpeta completa) a la rama `gh-pages`.

7. Se publicó el reporte con los siguientes comandos:

   ```bash
   git add .
   git commit -m "deploy: publish allure report"
   git push origin gh-pages --force
   ```

### 🎯 Resultado

El reporte de Allure fue publicado exitosamente utilizando GitHub Pages, quedando disponible mediante una URL pública para portafolio y demostración.

### 🧠 Lecciones clave

* Ignorar siempre archivos generados (`target/`, `allure-report/`)
* Usar una rama dedicada (`gh-pages`) para despliegue
* Asegurar que `index.html` esté en la raíz de la rama publicada
* No mezclar código fuente con archivos generados en la misma rama


//Configuracion de pages
PROYECTO / SETTINGS / PAGES /BRANCH / gh-pages /(root) / save

//actualizar reporte allure

mvn clean test
allure generate target/allure-results --clean -o allure-report
git checkout gh-pages
git rm -rf .
xcopy /E /I /Y allure-report\* .
git add .
git commit -m "update report"
git push origin gh-pages --force
