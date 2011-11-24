(ns diff-match-patch-clj.core
  (:require [clojure.string :as s])
  (:import [name.fraser.neil.plaintext diff_match_patch diff_match_patch$Operation]))

(defn make-diff-match-patch
  []
  (diff_match_patch.))

(defonce +dmp+ (make-diff-match-patch))

(defn diff [t1 t2]
  (.diff_main +dmp+ t1 t2))

(defn cleanup!
  [diffs]
  (.diff_cleanupSemantic +dmp+ diffs)
  diffs)

(defn pretty-html
  [diffs]
  (.diff_prettyHtml +dmp+ diffs))

(defn as-hiccup
  [diffs & {:keys [ins-attrs del-attrs equal-attrs]
            :or   {ins-attrs   nil
                   del-attrs   nil
                   equal-attrs nil}}]
  (map (fn [diff]
         ;; Gotta use condp as enums aren't compile time constants
         ;; therefore you can't use case, bummer.
         (condp = (. diff operation)
               diff_match_patch$Operation/INSERT
               [:ins ins-attrs (. diff text)]

               diff_match_patch$Operation/DELETE
               [:del del-attrs (. diff text)]

               diff_match_patch$Operation/EQUAL
               [:span equal-attrs (. diff text)]))
       diffs))
