;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-reader.ss" "lang")((modname fp) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))
;; =================
;; Data Definitions
;; =================

;; TrafficLight is one of:
;; - "red"
;; - "yellow"
;; - "green"
;; interp. represents states of a traffic light
(define RED "red")
(define YELLOW "yellow")
(define GREEN "green")

;; Template:
#;
(define (fn-for-traffic-light tl)
  (cond
    [(string=? tl RED) ...]
    [(string=? tl YELLOW) ...]
    [(string=? tl GREEN) ...]))

;; Person is (make-person String String)
;; interp. represents a person with their name and favorite food
(define-struct person (name favorite-food))

;; Template:
#;
(define (fn-for-person p)
  (...(person-name p)...
   ...(person-favorite-food p)...))

;; Crossing is (make-crossing TrafficLight TrafficLight)
;; interp. represents a crossing with horizontal and vertical traffic lights
(define-struct crossing (horizontal vertical))

;; =================
;; Functions
;; =================

;; Higher-order function example (similar to Scala's compose)
;; Number (Number -> Number) (Number -> Number) -> Number
;; Applies function g to the result of applying function f to n
(check-expect (my-compose 42 add1 (lambda (x) (* x 2))) 86)
(define (my-compose n f g)
  (g (f n)))

;; TrafficLight -> TrafficLight
;; Switches traffic light to next state in cycle
(check-expect (switch-traffic-light RED) GREEN)
(check-expect (switch-traffic-light YELLOW) RED)
(check-expect (switch-traffic-light GREEN) YELLOW)
(define (switch-traffic-light tl)
  (cond
    [(string=? tl RED) GREEN]
    [(string=? tl YELLOW) RED]
    [(string=? tl GREEN) YELLOW]))

;; List processing examples (similar to Scala's immutable collections)
;; List[Number] -> Number
;; Computes sum of all numbers in list
(check-expect (sum empty) 0)
(check-expect (sum (list 1 2 3)) 6)
(define (sum lst)
  (cond
    [(empty? lst) 0]
    [else (+ (first lst)
             (sum (rest lst)))]))

;; Crossing -> Boolean
;; Determines if traffic light configuration is allowed
;; Returns false if both lights are green or if one is yellow and other is green
(check-expect (allowed-config? (make-crossing GREEN GREEN)) false)
(check-expect (allowed-config? (make-crossing YELLOW GREEN)) false)
(check-expect (allowed-config? (make-crossing RED GREEN)) true)
(define (allowed-config? c)
  (cond
    [(and (string=? (crossing-horizontal c) GREEN)
          (string=? (crossing-vertical c) GREEN)) false]
    [(and (string=? (crossing-horizontal c) YELLOW)
          (string=? (crossing-vertical c) GREEN)) false]
    [(and (string=? (crossing-horizontal c) GREEN)
          (string=? (crossing-vertical c) YELLOW)) false]
    [else true]))

;; Example structures
(define p1 (make-person "Jonathan" "Burgers"))
(define c1 (make-crossing RED GREEN))