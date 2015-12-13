(ns ^:figwheel-always net.svard.weather-dashboard.utils.date
  (:import [goog.date DateTime]))

(defprotocol IFormat
  (-format-datetime [date]))

(extend-protocol IFormat
  string
  (-format-datetime [date]
    (-> (.fromRfc822String DateTime date)
      (.toIsoString true)))

  number
  (-format-datetime [date]
    (-> (.fromTimestamp DateTime date)
      (.toIsoString true))))

(defn format-datetime [date]
  (-format-datetime date))
