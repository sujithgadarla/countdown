(ns oscar-history.core)

(enable-console-print!)

(defonce target-date (js/Date. 2016 6 30 23 59 59))

(defonce iters (atom 0))

(defn init []
  (let [now (js/Date.)
        diff-ms (- (.getTime target-date) (.getTime now))
        diff-secs (quot diff-ms 1000)
        secs (rem diff-secs 60)
        diff-mins (quot diff-secs 60)
        mins (rem diff-mins 60)
        diff-hours (quot diff-mins 60)
        hours (rem diff-hours 24)
        days (quot diff-hours 24)]
    ;; (.log js/console "%d days, %d hours, %d mins, %d secs" days hours mins secs)
    (->> secs
         (set! (.-innerHTML (.getElementById js/document "secs"))))
    (->> mins
         (set! (.-innerHTML (.getElementById js/document "mins"))))
    (->> hours
         (set! (.-innerHTML (.getElementById js/document "hours"))))
    (->> days
         (set! (.-innerHTML (.getElementById js/document "days"))))
    ))

(set! (.-onload js/window) (fn []
                             (swap! iters inc)))

(.setInterval js/window init 100)
