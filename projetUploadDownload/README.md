## 1) Comment faire de l'upload en 20 lignes max?

On peut faire de l'upload de fichier grace au type "file" de l'input html.
Dans la servlet on va utiliser la méthode getParts() de l'API servlet 3.0 qui permet
de récupérer les données de type multipart.
Le fichier est ensuite récupéré par l'objet Part.
Que l'on utilise pour écrire le fichier dans le dossier de l'utilisateur.

## 2) Comment limiter les types de fichiers acceptés ?

En HTML on peut ajouter une propriété "accept" avec un MIME type dans la balise "input" afin de limiter les types de fichiers acceptés. 

## 3) Comment limiter la taille des fichiers acceptés ?

On peut limiter la taille en ajoutant une annotation @MultipartConfig dans la servlet d'upload.
On peut lui passer en parametre maxFileSize et maxRequestSize afin de définir
 les tailles maximum du fichier et de la requête.