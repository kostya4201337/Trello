import {IBoard} from "../../../model/Board";
    export type BoardProps = {
    board: IBoard,
    deleteBoard: (id: number) => void
}