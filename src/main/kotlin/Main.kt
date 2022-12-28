
fun setList(row : String){
    val temp  = row.split("-->","|")
    var nonTerminal = mutableListOf<String>()

    var terminal = temp[0]
    for (i in temp.indices){
        if(i!=0){
            nonTerminal.add(temp[i])
        }
    }
    terminalList.add(Terminal(terminal,nonTerminal))
}

fun check(expression: String) : Terminal?{
    terminalList.forEach {
        if (expression.contains(it.name)){
            return it
        }
    }
    return null
}

fun create(terminal: Terminal,expression: String){

    for (i in terminal.elements.indices){
        var newEx = expression.replaceFirst(terminal.name,terminal.elements[i])
        var check = check(newEx)

            if (check!=null){
                create(check,newEx)
            }
            else{
                addToCreatedList(newEx)
            }
        }

    }

fun addToCreatedList(expression: String){
    if(!createdWords.contains(expression)){
        createdWords.add(expression)

    }

}
data class Terminal(val name:String ,val elements: List<String>)

var terminalList : ArrayList<Terminal> = arrayListOf()

var createdWords = mutableListOf<String>()


fun main() {


    val cfg = "S-->aa|bX,X-->b"


    var rows = cfg.split(",")
    rows.map {
        setList(it)
    }

    terminalList[0].elements.map {
        var result = check(it)

        if (result!=null){
           create(result,it)
        }
        else{
            addToCreatedList(it)
        }
    }

    println(createdWords)

}