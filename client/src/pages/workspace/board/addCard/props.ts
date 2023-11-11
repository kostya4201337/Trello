import {ICardCreation} from "../../../../model/Card";

export type AddCardProp = {
    boardId: number
    addCard: (card: ICardCreation) => void
}