

(*
ÉVITONS D'ÉCRIRE DES PARSERS ... par Michaël Périn

Idée:
- fournissez vos données sous la forme d'une structure de données OCaml
- codez en Ocaml les fonctions de traduction des données vers le format de sortie souhaité
- réutilisez le parser et l'interprète ocaml pour traduire les données
dans le format de sortie souhaité

Exemple:
génération de liste au format html avec numérotation automatique des item

Utilisation: voir en fin de ce fichier
*)



(* 1. FONCTIONS AUXILLIAIRES DE TRADUCTION *)

let spaces (n:int) : string = String.make n ' '

let newline (indent:int) : string = "\n" ^ (spaces indent)

type xml = string

let xml_block (with_newline:bool) (indent:int) (name:string) (content:string) : xml =
  let opt_newline indent = if with_newline then newline indent else ""
  in
    let begin_xml_block name = (opt_newline indent) ^ "<" ^ name ^ ">"
    and end_xml_block name = "</" ^ name ^ ">"
    in
      (begin_xml_block name) ^ content ^ (end_xml_block name)
      
let make_section (section:string) (i:int) : string =
  (if section = "" then "" else section ^ ".")
  ^
  (if i=0 then "" else string_of_int i)

let (<.>) = make_section



(* STRUCTURE DES DONNÉES écrites directement en OCAML *)

type etat = string
type nom = string
type action = string
type listeAction = action list
type condition = string
type listeCondition = condition list
type transition = etat * listeCondition * listeAction * etat
type listeTransition = transition list
type automate = nom * listeTransition
type automateListe = automate list
type input = automateListe

let rec stringList_to_string l = match l with
  |[] -> ""
  |p::r -> p ^ stringList_to_string r;;

let automatePacman=("Pacman",
		    [("0",["rien"],["avancer"],"1");
		     ("1",["rien"],["avancer"],"1")
		    ])
let automateFantome=("Fantome",
		     [("0",["rien"],["avancer"],"1");
		      ("1",["rien"],["changersens"],"2");
		      ("2",["rien"],["avancer"],"1");
		     ])
let input = [automatePacman;automateFantome]


(* FONCTION PRINCIPALE DE TRADUCTION *)

let parser_string n s =
  if n='c'
  then xml_block true 20 "Condition" s
  else xml_block true 20 "Action" s;;

let rec parser_liste l n =
  if n='c'
  then match l with
    |[] -> ""
    |l -> xml_block true 16 "Conditions" ((stringList_to_string (List.map (parser_string n) l)))
  else match l with
    |[] -> ""
    |l -> xml_block true 16 "Actions" ((stringList_to_string (List.map (parser_string n) l)));;

let parser_etat e n =
  if n='i'
  then xml_block true 16 "EtatCourant" e
  else xml_block true 16 "EtatFinal" e;;

let parser_transition t =
  let (ei,lc,la,ef) = t
  in xml_block true 12 "Transition" (parser_etat ei 'i' ^ parser_liste lc 'c' ^ parser_liste la 'a' ^ parser_etat ef 'f');;


let parser_nom n = xml_block true 8 "Nom" n;;

let rec parser_listeTransition l = match l with
  |[] -> ""
  |l -> xml_block true 8 "Transitions" ((stringList_to_string(List.map parser_transition l)));;

let rec parser_automate aut =
  let (nom,lt) = aut
  in xml_block true 4 "Automate" (parser_nom nom ^ parser_listeTransition lt);;


let parser_automateListe automates = match automates with
  |[] -> ""
  |l -> xml_block true 0 "Automates" (stringList_to_string (List.map parser_automate l));;

parser_automateListe input;;


let ecrireXML =
  let output = open_out "automate.xml" in
  output_string output (parser_automateListe input);
  close_out output;;

ecrireXML;; 
