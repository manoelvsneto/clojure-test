(ns clojure-crud-api.repository.migrations
  (:require [clojure.java.jdbc :as sql]))

(def db {:classname "com.microsoft.jdbc.sqlserver.SQLServerDriver"
         :dbtype "mssql"
         :dbname "clojure"
         :host "localhost"
         :port "1433"
         :user "sa"
         :password "1265946"})

(def authors-table-ddl
  (sql/create-table-ddl "authors" [[:id "INT IDENTITY(1,1) PRIMARY KEY"]
                                   [:name "VARCHAR(150) NOT NULL"]
                                   [:description "VARCHAR(250) NOT NULL"]
                                   [:created_at :timestamp "DATETIME NULL"]]))

(def books-table-ddl
  (sql/create-table-ddl "books" [[:id "INT IDENTITY(1,1) PRIMARY KEY"]
                                 [:title "VARCHAR(150) NOT NULL"]
                                 [:length "INT NOT NULL"]
                                 [:genre "VARCHAR(250) NOT NULL"]
                                 [:author_id "INT NOT NULL"]
                                 [:created_at :timestamp "DATETIME NULL"]]))
(defn migrated? [table-name]
  (-> (sql/query db
                 [(str "select count(0) from sys.objects  where type = 'U' and name ='" table-name "'")])
      first :count pos?))

(defn run-migrations []
  ;(when (not (migrated? "authors"))
   ; (print "Creating authors database...") (flush)
  ; (sql/db-do-commands db
   ;                     [authors-table-ddl]))

 ;(when (not (migrated? "books"))
 ;   (print "Creating books database...") (flush)
  ;  (sql/db-do-commands db
   ;                     [books-table-ddl]))
  )

; Extension to convert arrays to sql arrays
(extend-protocol clojure.java.jdbc/ISQLParameter
  clojure.lang.IPersistentVector
  (set-parameter [v ^java.sql.PreparedStatement stmt ^long i]
    (let [conn (.getConnection stmt)
          meta (.getParameterMetaData stmt)
          type-name (.getParameterTypeName meta i)]
      (if-let [elem-type (when (= (first type-name) \_) (apply str (rest type-name)))]
        (.setObject stmt i (.createArrayOf conn elem-type (to-array v)))
        (.setObject stmt i v)))))

(extend-protocol clojure.java.jdbc/IResultSetReadColumn
  java.sql.Array
  (result-set-read-column [val _ _]
    (into [] (.getArray val))))