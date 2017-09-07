(set-env!
  :dependencies '[[adzerk/boot-cljs          "1.7.228-2"]
                  [adzerk/boot-cljs-repl     "0.3.3"]
                  [adzerk/boot-reload        "0.4.13"]
                  [hoplon/hoplon             "6.0.0-alpha17"]
                  [org.clojure/clojure       "1.8.0"]
                  [org.clojure/clojurescript "1.9.655"]
                  [org.clojure/test.check    "0.9.0"]
                  [tailrecursion/boot-jetty  "0.1.3"]
                  [com.cemerick/piggieback   "0.2.1"  :scope "test"]
                  [weasel                    "0.7.0"  :scope "test"]
                  [org.clojure/tools.nrepl   "0.2.12" :scope "test"]
                  [eval-soup                 "1.2.2"]
                  [pandeiro/boot-http        "0.8.3"  :scope "test"]]
  :source-paths #{"src"}
  :asset-paths  #{"assets"})

(require
  '[adzerk.boot-cljs         :refer [cljs]]
  '[adzerk.boot-cljs-repl    :refer [cljs-repl start-repl]]
  '[adzerk.boot-reload       :refer [reload]]
  '[hoplon.boot-hoplon       :refer [hoplon prerender]]
  ;; '[tailrecursion.boot-jetty :refer [serve]]
  '[pandeiro.boot-http :refer [serve]])

(deftask dev []
  (comp (serve)
        (watch)
        (cljs-repl) ; order is important!!
        (cljs)))

#_(deftask dev
  "Build specling for local development."
  []
  (comp
    (watch)
    (speak)
    (hoplon)
    (reload)
    (cljs-repl)
    (cljs)
    (serve :port 8000)))

(deftask prod
  "Build specling for production deployment."
  []
  (comp
    (hoplon)
    (cljs :optimizations :advanced)
    (target :dir #{"target"})))
